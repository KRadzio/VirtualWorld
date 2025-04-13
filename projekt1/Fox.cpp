#include "Fox.h"
Fox::Fox(World &wR, const int &xPos, const int &yPos)
    : Animal(wR, xPos, yPos, SYMBOL, STRENGTH, INITIATIVE, AllOrganisms::FOX) {this->typeS = "Lis";}
Fox::~Fox() {}
void Fox::takeAction()
{
    this->prevX = this->yPos;
    this->prevY = this->yPos;
    int newY = this->yPos;
    int newX = this->xPos;
    bool isMoveSafe = false;
    while (isMoveSafe == false)
    {
        bool moveIsValid = false;
        while (moveIsValid == false)
        {
            int random = rand() % 4;
            switch (random)
            {
            case 0:
                if (this->worldRef.isPositionInsideWorld(newX, newY - this->moveRange))
                {
                    newY--;
                    moveIsValid = true;
                }
                break;
            case 1:
                if (this->worldRef.isPositionInsideWorld(newX, newY + this->moveRange))
                {
                    newY++;
                    moveIsValid = true;
                }
                break;
            case 2:
                if (this->worldRef.isPositionInsideWorld(newX + this->moveRange, newY))
                {
                    newX++;
                    moveIsValid = true;
                }
                break;
            case 3:
                if (this->worldRef.isPositionInsideWorld(newX - this->moveRange, newY))
                {
                    newX--;
                    moveIsValid = true;
                }
                break;
            }
        }

        if (this->worldRef.isPositionFree(newX, newY) != true && this->worldRef.getOrganism(newX, newY)->getStrength() <= this->strength)
        {
            isMoveSafe = true;
            colision(this->worldRef.getOrganism(newX, newY));
        }
        else if (this->worldRef.isPositionFree(newX, newY))
            isMoveSafe = true;
    }
     if(this->worldRef.isPositionFree(newX, newY) || this->worldRef.getOrganism(newX, newY)->checkIfIsAlive() == false)
        move(newX, newY);
}