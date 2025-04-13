#include "Antelope.h"
Antelope::Antelope(World &wR, const int &xPos, const int &yPos)
    : Animal(wR, xPos, yPos, SYMBOL, STRENGTH, INITIATIVE, AllOrganisms::ANTELOPE)
{
    this->moveRange = MOVE_RANGE;
    this->typeS = "Antylopa";
}
Antelope::~Antelope() {}
bool Antelope::specialAbility(Organism *other)
{
    if (rand() % 2 == 0)
    {
        this->prevX = this->yPos;
        this->prevY = this->yPos;
        int newY = this->yPos;
        int newX = this->xPos;
        bool moveIsValid = false;
        while (moveIsValid == false)
        {
            int random = rand() % 4;
            switch (random)
            {
            case 0:
                if (this->worldRef.isPositionInsideWorld(newX, newY - 1) && this->worldRef.isPositionFree(newX, newY - 1))
                {
                    newY--;
                    moveIsValid = true;
                }
                break;
            case 1:
                if (this->worldRef.isPositionInsideWorld(newX, newY + 1) && this->worldRef.isPositionFree(newX, newY + 1))
                {
                    newY++;
                    moveIsValid = true;
                }
                break;
            case 2:
                if (this->worldRef.isPositionInsideWorld(newX + 1, newY) && this->worldRef.isPositionFree(newX + 1, newY))
                {
                    newX++;
                    moveIsValid = true;
                }
                break;
            case 3:
                if (this->worldRef.isPositionInsideWorld(newX - 1, newY) && this->worldRef.isPositionFree(newX - 1, newY))
                {
                    newX--;
                    moveIsValid = true;
                }
                break;
            }
        }
        string event = this->typeS + " uciekla przed " + other->getTypeString();
        this->worldRef.addEvent(event);
         if(this->worldRef.isPositionFree(newX, newY) || this->worldRef.getOrganism(newX, newY)->checkIfIsAlive() == false)
        move(newX, newY);
        return true;
    }
    return false;
}
void Antelope::takeAction()
{
    this->prevX = this->yPos;
    this->prevY = this->yPos;
    int newY = this->yPos;
    int newX = this->xPos;
    bool moveIsValid = false;
    while (moveIsValid == false)
    {
        int random = rand() % 4;
        switch (random)
        {
        case 0:
            if (this->worldRef.isPositionInsideWorld(newX, newY - MOVE_RANGE))
            {
                newY -= MOVE_RANGE;
                moveIsValid = true;
            }
            break;
        case 1:
            if (this->worldRef.isPositionInsideWorld(newX, newY + MOVE_RANGE))
            {
                newY += MOVE_RANGE;
                moveIsValid = true;
            }
            break;
        case 2:
            if (this->worldRef.isPositionInsideWorld(newX + MOVE_RANGE, newY))
            {
                newX += MOVE_RANGE;
                moveIsValid = true;
            }
            break;
        case 3:
            if (this->worldRef.isPositionInsideWorld(newX - MOVE_RANGE, newY)) // zwierzeta wychodza
            {
                newX -= MOVE_RANGE;
                moveIsValid = true;
            }
            break;
        }
    }

    if (this->worldRef.isPositionFree(newX, newY) != true)
        colision(this->worldRef.getOrganism(newX, newY));
    move(newX, newY);
}