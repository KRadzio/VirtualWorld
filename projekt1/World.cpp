#include "World.h"
#include "AllOrganisms.h"
#define WORLD_DISPLAY_OFFSET 64
#define EVENTS_POS 11

void World::setSize(int size)
{
    this->size = size;
}

void World::passTurn()
{
    this->turn++;
    for (int i = 0; i < this->organisms.size(); i++)
    {
        this->organisms[i]->setAge(this->organisms[i]->getAge() + 1);
        this->organisms[i]->takeAction();
    }
}

void World::drawWorld()
{
    printf("%c[%d;%df", 0x1B, 1, WORLD_DISPLAY_OFFSET); // "gotoxy"
    for (int x = 0; x < this->size + 2; x++)
        cout << '*';
    cout << endl;
    for (int y = 0; y < this->size; y++)
    {
        printf("%c[%d;%df", 0x1B, y + 2, WORLD_DISPLAY_OFFSET);
        cout << '|';
        for (int x = 0; x < this->size; x++)
        {
            bool isDisplayed = false;
            for (int i = 0; i < this->organisms.size(); i++)
            {
                if (this->organisms[i]->getXPos() == x && this->organisms[i]->getYPos() == y)
                {
                    organisms[i]->drawOrg();
                    isDisplayed = true;
                    break;
                }
            }
            if (isDisplayed == false)
                cout << ' ';
        }
        cout << '|' << endl;
    }
    printf("%c[%d;%df", 0x1B, this->size + 2, WORLD_DISPLAY_OFFSET);
    for (int x = 0; x < this->size + 2; x++)
        cout << '*';
    cout << endl;
}

void World::drawEvents()
{
    printf("%c[%d;%df", 0x1B, EVENTS_POS, 1);
    for (int i = 0; i < events.size(); i++)
    {
        cout << events[i] << endl;
    }
}

void World::randomOrganism(int xPos, int yPos)
{
    int random = rand() % 10;
    Organism *org;
    switch (random)
    {
    case 0:
        org = new Wolf(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case 1:
        org = new Sheep(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case 2:
        org = new Fox(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case 3:
        org = new Turtle(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case 4:
        org = new Antelope(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case 5:
        org = new Grass(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case 6:
        org = new Dandelion(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case 7:
        org = new Guarana(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case 8:
        org = new Nightshade(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case 9:
        org = new PineBorscht(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    }
}

void World::setOrganisms()
{
    Human *hum = new Human(*this, this->size / 2, this->size / 2);
    this->hum = hum;
    Organism *org = hum;
    this->organisms.push_back(org);
    for (int i = 0; i < size; i++) // size + 1 swiata jest zapelniona na poczatku
    {
        int x = rand() % this->size;
        int y = rand() % this->size;
        bool isSet = false;
        while (isSet == false)
        {
            if (isPositionFree(x, y))
                isSet = true;
            else
            {
                x = rand() % this->size;
                y = rand() % this->size;
            }
        }
        randomOrganism(x, y);
        //Organism *org = new Sheep(*this, x, y); // do destowania rozmnarzania
        //this->organisms.push_back(org);
    }
}

void World::clearWorld() { this->organisms.clear(); }

void World::removeOrganisms()
{
    for (int i = 0; i < this->organisms.size(); i++)
    {
        if (this->organisms[i]->checkIfIsAlive() == false)
        {
            this->organisms.erase(this->organisms.begin() + i);
            i--;
        }
    }
}

void World::clearEvents() { this->events.clear(); }

void World::sortOrganisms()
{
    for (int i = 1; i < this->organisms.size(); i++) // inicjatywa
    {
        Organism *temp;
        for (int j = this->organisms.size() - 1; j >= i; j--)
        {
            if (this->organisms[j - 1]->getInitiative() < this->organisms[j]->getInitiative())
            {
                temp = this->organisms[j - 1];
                this->organisms[j - 1] = this->organisms[j];
                this->organisms[j] = temp;
            }
        }
    }
    for (int i = 1; i < this->organisms.size(); i++) // wiek
    {
        Organism *temp;
        for (int j = this->organisms.size() - 1; j >= i; j--)
        {
            if (this->organisms[j - 1]->getAge() < this->organisms[j]->getAge() && this->organisms[j - 1]->getInitiative() == this->organisms[j]->getInitiative())
            {
                temp = this->organisms[j - 1];
                this->organisms[j - 1] = this->organisms[j];
                this->organisms[j] = temp;
            }
        }
    }
}

bool World::isPositionFree(int x, int y) const
{
    for (int i = 0; i < this->organisms.size(); i++)
    {
        if (this->organisms[i]->getXPos() == x && this->organisms[i]->getYPos() == y)
            return false;
    }
    return true;
}

Organism *World::getOrganism(int x, int y)
{
    for (int i = 0; i < this->organisms.size(); i++)
    {
        if (this->organisms[i]->getXPos() == x && this->organisms[i]->getYPos() == y)
            return organisms[i];
    }
}

bool World::isPositionInsideWorld(int x, int y) const
{
    if (x < 0 || x >= this->size || y < 0 || y >= this->size)
        return false;
    else
        return true;
}

void World::addEvent(string event)
{
    this->events.push_back(event);
}

void World::addNewOrganism(Organism *parent, int xPos, int yPos)
{
    Organism *org;
    switch (parent->getType())
    {
    case Organism::AllOrganisms::WOLF:
        org = new Wolf(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case Organism::AllOrganisms::SHEEP:
        org = new Sheep(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case Organism::AllOrganisms::FOX:
        org = new Fox(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case Organism::AllOrganisms::TURTLE:
        org = new Turtle(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case Organism::AllOrganisms::ANTELOPE:
        org = new Antelope(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case Organism::AllOrganisms::GRASS:
        org = new Grass(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case Organism::AllOrganisms::DANDELION:
        org = new Dandelion(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case Organism::AllOrganisms::GUARANA:
        org = new Guarana(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case Organism::AllOrganisms::NIGHTSHADE:
        org = new Nightshade(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    case Organism::AllOrganisms::PINEBORSCHT:
        org = new PineBorscht(*this, xPos, yPos);
        this->organisms.push_back(org);
        break;
    default:
        return;
    }
}

bool World::isHumanAlive() const { return this->hum->checkIfIsAlive(); }

int World::getSize() const { return this->size; }

int World::getTurn() const { return this->turn; }

World::World() {}
World::~World() {}